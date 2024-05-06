package com.example.homework_2.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.homework_2.data.db.dao.MessageDao
import com.example.homework_2.data.db.dao.ProfileDao
import com.example.homework_2.data.db.dao.StreamDao
import com.example.homework_2.data.db.entity.MessageDbItem
import com.example.homework_2.data.db.entity.ProfileDbItem
import com.example.homework_2.data.db.entity.ReactionDBItem
import com.example.homework_2.data.db.entity.StreamDbItem
import com.example.homework_2.data.db.entity.TopicDbItem

@Database(entities = [
    ProfileDbItem::class,
    MessageDbItem::class,
    StreamDbItem::class,
    TopicDbItem::class,
    ReactionDBItem::class
], version = 7)
abstract class DataBase: RoomDatabase() {

    abstract fun profileDao(): ProfileDao

    abstract fun streamDao(): StreamDao

    abstract fun messageDao(): MessageDao
}