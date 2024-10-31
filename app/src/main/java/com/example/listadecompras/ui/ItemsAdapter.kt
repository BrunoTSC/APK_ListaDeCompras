package com.example.listadecompras.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.model.ItemModel
import com.example.listadecompras.R

class ItemsAdapter: RecyclerView.Adapter<ItemsAdapter.ItemViewHolder>() {

    private val items = mutableListOf<ItemModel>() //Para isso, vamos definir uma variável na classe que conterá os itens a serem exibidos:

    fun addItem(newItem: ItemModel) { //E vamos definir uma função para atualizar essa lista
        items.add(newItem)
        notifyDataSetChanged() // A função notifyDataSetChanged avisa à lista que existem  itens novos e que ela deve recarregar.
    }

    fun removeItem(item: ItemModel){ //Remover um item da lista não é nada complicado. Podemos criar um método removeItem no Adapter passando como parâmetro o objeto que desejamos remover da lista. Lembre-se: não manipulamos os itens da lista diretamente, manipulamos o adaptador e ele refletirá as alterações na lista!
        items.remove(item) //para remover de fato, estamos utilizando a função "remove" da lista.
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder { // essa função deverá criar uma instância da classe ViewHolder ;
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ItemViewHolder(view)
    }

    /*Para obter uma instância da View , vamos utilizar o
LayoutInflater e inflar o arquivo xml que contém o layout que
queremos. Primeiro, vamos inicializar o LayoutInflater a partir
de um contexto, nesse caso, vamos aproveitar o context do
parâmetro parent :
LayoutInflater.from(parent.context)
Vamos utilizar a função inflate para inflar o arquivo xml e
definir o resultado de tudo isso em uma variável chamada view :
val view = LayoutInflater.from(parent.context).inflate(R.layout.i
tem_layout, parent, false)
E retornar uma instância de ItemViewHolder passando o
objeto view em seu construtor:*/

    override fun getItemCount(): Int = items.size  // essa função deverá retornar a quantidade de itens na lista;
    /*Agora, voltando para a função getItemCount , a
implementação dela é muito simples, devemos simplesmente
retornar o tamanho da variável items : override fun getItemCount(): Int = items.size
  */

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) { // essa função deverá fazer a ligação  entre as informações que estão na lista e os elementos do ViewHolder .
        /* partindo para a função onBindViewHolder . Veja
que ela recebe dois parâmetros: holder: ItemViewHolder e
position: Int . O parâmetro holder é uma instância da nossa
ViewHolder e o position é a posição da lista que deve ser
exibida na tela naquele momento. Sabendo disso, vamos acessar a
variável items na posição position e aí teremos uma instância

da classe ItemModel . Essa instância será utilizada na chamada da
função bind do ViewHolder .*/
        val item = items[position]
        holder.bind(item)
    }

    class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) { // A classe-base RecyclerView.ViewHolder recebe uma instância de View , que é o componente visual em si, então vamos  defini-lo no construtor
        val textView = view.findViewById<TextView>(R.id.textViewItem) // Agora vamos atualizar os valores na tela. Primeiro vamos obter uma instância da TextView utilizando o comando findViewById e atualizar a propriedade text com o valor do ItemModel :
        val button = view.findViewById<ImageButton>(R.id.imageButton) //definindo o click do ícone da lixeira para remover o item.
        fun bind(item: ItemModel) { //A função bind do ViewHolder será responsável por receber os dados e exibi-los na tela, ou seja, através dessa função vamos acessar os elementos visuais e atualizá-los conforme os dados recebidos.
            textView.text = item.name

            button.setOnClickListener{ //definindo o click do ícone da lixeira para remover o item.
                item.onRemove(item)
            }
        }
    }
}