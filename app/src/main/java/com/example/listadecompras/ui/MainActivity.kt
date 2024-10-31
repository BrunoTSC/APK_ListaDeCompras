package com.example.listadecompras.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.listadecompras.model.ItemModel
import com.example.listadecompras.viewmodel.ItemsViewModel
import com.example.listadecompras.viewmodel.ItemsViewModelFactory
import com.example.listadecompras.R

/*Agora que já criamos nosso Adapter , falta ligá-lo na
RecyclerView . O processo é muito simples. A RecyclerView
possui um atributo chamado adapter , que é justamente para
definição do adaptador da lista. Pensando no exemplo da tomada
trazido anteriormente, é como se o atributo adapter da
RecyclerView fosse o plugue da tomada em que ligaremos o
adaptador.*/

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) { //método onCreate implementado dentro da classe.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // dnetro do método temos essa chamada para o conteúdo da tela.

        val viewModel: ItemsViewModel by viewModels {
            ItemsViewModelFactory(applicationContext)
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recycleView);
        val itemsAdapter = ItemsAdapter()
        recyclerView.adapter = itemsAdapter
        val button = findViewById<Button>(R.id.txtButton)
        val editText = findViewById<EditText>(R.id.txtProduto)
        button.setOnClickListener{

            if (editText.text.isEmpty()){ //o método isEmpty(está vazio) é booleano e seu retorno será true ou false.
                editText.error = "Preencha um valor" // a propriedade "error" exibe a mensagem de erro para o usuário.
                return@setOnClickListener //o return é para parar a execução da função naquele momento. E o @setOnClickListener depois do return indica ao compilador que o return é especificamente para o listener do botão.
            }
            val itemName = editText.text.toString()
            viewModel.addItem(itemName)

            val item = ItemModel(
                name = editText.text.toString(),
                onRemove = { // passamos o valor da função onRemove, e ela executará a remoção do item pelo Adapter.
                    itemsAdapter.removeItem(it)
                }
            )
            itemsAdapter.addItem(item)
            editText.text.clear() //método clear para limpar o campo após a inserção do produto.
        }
    }
}

/* Nossa tarefa agora é programar a lógica do nosso aplicativo.
Não será uma lógica complexa: nós simplesmente queremos que o
produto seja inserido na lista após o usuário preencher o nome do

produto e clicar no botão inserir . Para isso, precisamos
entender como as listas funcionam e como podemos inserir novos
itens nelas.*/