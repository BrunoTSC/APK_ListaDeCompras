package com.example.listadecompras.data;


import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.example.listadecompras.model.ItemModel;


@Entity (tableName = "items")// anotamos o arquivo com o @Entity para indicar que é uma entidade de banco de dados.
data class ItemEntity (
    @PrimaryKey(autoGenerate = true) //vamos definir suas propriedades , que serão colunas no banco de dados.
    val id: Long, // O id será uma chave primária do banco de dados, isto é, esse id não pode se repetir, por isso o anotaremos com @PrimaryKey; para deixar o próprio BD criar os ids automaticamente, vamos passar também parâmetro autoGenerate = true.
    val name: String
)

fun ItemEntity.toModel(onRemove: (ItemModel) -> Unit): ItemModel {
    return ItemModel(
        id = this.id,
        name = this.name,
        onRemove = onRemove
    )
}