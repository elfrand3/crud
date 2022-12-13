package com.frand.crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.frand.crud.adapter.FileAdapter
import com.frand.crud.databinding.ActivityMainBinding
import com.frand.crud.model.DataItem
import com.frand.crud.model.ResponseGet
import com.frand.crud.network.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    val mainAdapter = FileAdapter(arrayListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val tgs = binding.tc
        tgs.setOnClickListener{startActivity(Intent(this@MainActivity, ProductsActivity::class.java))}


        mainAdapter.setOnItemClickListener(object : FileAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {
                val builder = AlertDialog.Builder(this@MainActivity)

                //Kebutuhan konten alert dialog
                builder.setTitle(R.string.jd)
                builder.setMessage(R.string.pd)
                builder.setIcon(R.drawable.ic_warning)

                //Membuat action Button pada Alert Dialog
                //Teks (_ , _) ini merupakan nama parameter
                builder.setPositiveButton("Ya") { _, _ ->
                    Toast.makeText(applicationContext, "Klik ya", Toast.LENGTH_SHORT).show()
                }
                builder.setNegativeButton("Tidak") { _, _ ->
                    Toast.makeText(applicationContext, "Klik Tidak", Toast.LENGTH_SHORT).show()
                }
                builder.setNeutralButton("Batal") { _, _ ->
                    Toast.makeText(applicationContext, "Klik Batal", Toast.LENGTH_SHORT).show()
                }

                //Menampilkan kelas alert dialog
                val tampil : AlertDialog = builder.create()
                tampil.setCancelable(false)
                tampil.show()
            }
        })
        supportActionBar?.hide()
        rv_list.layoutManager = GridLayoutManager(this, 1)
        rv_list.adapter = mainAdapter
        getData()
    }

    private fun getData() {
        ApiService.endpoint.getData().enqueue(object : Callback<ResponseGet> {
            override fun onResponse(call: Call<ResponseGet>, response: Response<ResponseGet>) {
                if(response.isSuccessful){
                    val responseGet:ResponseGet? = response.body()
                    onResultData(responseGet!!)
                }
            }

            override fun onFailure(call: Call<ResponseGet>, t: Throwable) {
            }

        })
    }

    private fun onResultData(responseGet: ResponseGet) {
        val vertical = responseGet.data
        mainAdapter.getData(vertical as List<DataItem>)
    }

}