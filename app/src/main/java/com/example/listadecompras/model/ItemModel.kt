package com.example.listadecompras.model

import com.example.listadecompras.data.ItemEntity

data class ItemModel(val id: Long = 0, val name: String, val onRemove:(ItemModel) -> Unit) {
} //Se você quiser que o id seja gerado automaticamente ou não precise especificá-lo na interface defina 0 para o id long.
fun ItemModel.toEntity(): ItemEntity {
    return ItemEntity(
        id = this.id,
        name = this.name
    )
}


/*Vamos precisar definir uma função de remover na classe
ItemModel . Essa função não vai saber exatamente como remover
um item porque isso será responsabilidade do Adapter .

A função que vamos criar no ItemModel deverá somente ter uma
assinatura do que fazer quando um item for removido.
Abra o arquivo ItemModel e adicione a função onRemove
em seu construtor:
Veja que, aqui, o onRemove é simplesmente uma função que
recebe um ItemModel e retorna Unit .*/

/* O próximo passo será disparar essa função. Sabemos que o
onRemove deve acontecer quando o usuário clicar no ícone de
lixeira. Então abra o arquivo ItemsAdapter e vamos definir o
click do ícone dentro da ViewHolder :*/