package com.example.listadecompras.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [ItemEntity::class], version = 1 )
abstract class ItemsDatabase : RoomDatabase() {
    abstract fun itemsDao(): ItemsDao

    companion object {
        // Volatile para garantir que a instância seja visível para todos os threads
        @Volatile
        private var INSTANCE: ItemsDatabase? = null

        // Função para obter a instância do banco de dados
        fun getDatabase(context: Context): ItemsDatabase {
            // Se a instância já existir, retorná-la
            return INSTANCE ?: synchronized(this) {
                // Criar o banco de dados se ele ainda não existir
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ItemsDatabase::class.java,
                    "databaseBT_Lista"
                ).fallbackToDestructiveMigration() // Adiciona fallback
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}





/*    val db = databaseBuilder(
        applicationContext,
        ItemsDatabase::class.java, "database-name"
    ).build()
}*/
