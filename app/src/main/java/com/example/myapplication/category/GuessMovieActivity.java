package com.example.myapplication.category;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GuessMovieActivity extends AppCompatActivity {

    private ImageView imageViewGuess;
    private TextView textResult;
    private Button button0;
    private Button button1;
    private Button button2;
    private Button button3;

    private String url = "https://www.kinoafisha.info/rating/movies/";

    private ArrayList<String> urls;
    private ArrayList<String> names;
    private ArrayList<Button> buttons;

    private int numberOfQuestion;
    private int numberOfRightAnswer;

    private int resultGame = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_movie);

        imageViewGuess = findViewById(R.id.image_guess);
        textResult = findViewById(R.id.text_result);
        String result = String.format(getString(R.string.text_result), resultGame);
        textResult.setText(result);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);

        urls = new ArrayList<>();
        names = new ArrayList<>();

        buttons = new ArrayList<>();
        buttons.add(button0);
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);

        getContent();
        playGame();

        for (Button button : buttons) {
            button.setOnClickListener(this::onClickAnswer);
        }
    }


    public void onClickAnswer(View view) {
        Button button = (Button) view;
        String tag = button.getTag().toString();
        if (Integer.parseInt(tag) == numberOfRightAnswer) {
            Toast.makeText(this, "Верно!", Toast.LENGTH_SHORT).show();
            resultGame+=10;
        } else {
            Toast.makeText(this, "Неверно, правильный ответ" + names.get(numberOfQuestion), Toast.LENGTH_SHORT).show();
            resultGame-=5;
        }
        playGame();
    }

    private void playGame() {
        if (names.isEmpty() || urls.isEmpty()) {
            Log.e("MyResult", "Списки имен или URL пусты.");
            return;
        }
        try {
            textResult.setText(String.format(getString(R.string.text_result), resultGame));
            generateQuestion();
            DownloadImageTask task = new DownloadImageTask();
            Bitmap bitmap = task.execute(urls.get(numberOfQuestion)).get();
            if (bitmap != null) {
                imageViewGuess.setImageBitmap(bitmap);
                for (int i = 0; i < buttons.size(); i++) {
                    if (i == numberOfRightAnswer) {
                        buttons.get(i).setText(names.get(numberOfQuestion));
                    } else {
                        int wrongAnswer = generateWrongAnswer();
                        buttons.get(i).setText(names.get(wrongAnswer));
                    }
                }
            }
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateQuestion() {
        numberOfQuestion = (int) (Math.random() * names.size());
        numberOfRightAnswer = (int) (Math.random() * buttons.size());
    }

    private int generateWrongAnswer() {
        return (int) (Math.random() * names.size());
    }

    private void getContent() {
        DownloadContentTask task = new DownloadContentTask();
        try {
            String content = task.execute(url).get();
            if (content == null || content.isEmpty()) {
                Log.e("MyResult", "Полученный контент пуст.");
                return;
            }
            String start = "<div class=\"site_content\">";
            String finish = "Страна\t\t\t\t\t</div>";
            Pattern pattern = Pattern.compile(start + "(.*?)" + finish);
            Matcher matcher = pattern.matcher(content);
            String splitContent = "";
            while (matcher.find()) {
                splitContent = matcher.group(1);
            }
            assert splitContent != null;
            if (splitContent.isEmpty()) {
                Log.e("MyResult", "Не удалось найти нужный контент.");
                return;
            }
            Pattern patternName = Pattern.compile("<span class=\"movieItem_year\">(.*?)</span>");
            Pattern patternImage = Pattern.compile("<template><source srcset=\"(.*?)\" type=\"");
            Matcher matcherName = patternName.matcher(splitContent);
            Matcher matcherImage = patternImage.matcher(splitContent);
            while (matcherName.find()) {
                names.add(matcherName.group(1));
                Log.i("MyResult", Objects.requireNonNull(matcherName.group(1)));
            }
            while (matcherImage.find()) {
                urls.add(matcherImage.group(1));
                Log.i("MyResult", Objects.requireNonNull(matcherImage.group(1)));
            }
        } catch (ExecutionException | InterruptedException e) {
            Log.e("MyResult", "Ошибка при получении контента: " + e.getMessage());
        }
    }

    private static class DownloadContentTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder result = new StringBuilder();
            URL url = null;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String line = bufferedReader.readLine();
                while (line != null) {
                    result.append(line);
                    line = bufferedReader.readLine();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
            return result.toString();
        }
    }

    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            StringBuilder result = new StringBuilder();
            URL url = null;
            HttpURLConnection urlConnection = null;
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                return BitmapFactory.decodeStream(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
            }
        }
    }
}