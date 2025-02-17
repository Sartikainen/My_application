package com.example.myapplication.usersDB;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> users = new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public UserAdapter() {
        loadUsers();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    @SuppressLint("NotifyDataSetChanged")
    private void loadUsers() {
        db.collection("users")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        users.clear(); // Очищаем старые данные перед добавлением новых
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            User user = document.toObject(User.class);
                            users.add(user);
                        }
                        notifyDataSetChanged(); // Обновляем адаптер после загрузки данных
                    } else {
                        Log.e("Firebase", "Ошибка загрузки пользователей", task.getException());
                    }
                });
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new UserViewHolder((ViewGroup) view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = users.get(position);
        holder.tvName.setText(user.getName());
        holder.tvLastname.setText(user.getLastname());
        holder.tvSex.setText(user.getSex());
        holder.tvAge.setText(String.valueOf(user.getAge()));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView tvName;
        private TextView tvLastname;
        private TextView tvSex;
        private TextView tvAge;

        public UserViewHolder(@NonNull ViewGroup itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvLastname = itemView.findViewById(R.id.tvLastname);
            tvSex= itemView.findViewById(R.id.tvSex);
            tvAge= itemView.findViewById(R.id.tvAge);
        }
    }
}
