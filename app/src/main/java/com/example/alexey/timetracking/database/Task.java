package com.example.alexey.timetracking.database;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.UUID;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(tableName = "Tasks")
public class Task {
    @PrimaryKey
    @NonNull
    private String id = UUID.randomUUID().toString();
    private String name;
    private String description;
    private String time;

    @Override
    public String toString() {
        return String.format("%s | %s | %s;", name, description, time);
    }
}
