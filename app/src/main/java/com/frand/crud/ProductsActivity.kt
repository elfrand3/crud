package com.frand.crud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.frand.crud.adapter.ProductsAdapter
import com.frand.crud.databinding.ActivityProductsBinding
import com.frand.crud.model.ProductsItem
import com.frand.crud.model.ResponseProducts
import com.frand.crud.network.ApiProducts
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_products.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductsActivity : AppCompatActivity() {
    private val binding: ActivityProductsBinding by lazy {
        ActivityProductsBinding.inflate(layoutInflater)
    }

    val ProductsAdapter = ProductsAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()
        rv_products.layoutManager = LinearLayoutManager(this)
        rv_products.adapter = ProductsAdapter
        getProducts()
    }

    private fun getProducts() {
        ApiProducts.endpoint.getProducts().enqueue(object : Callback<ResponseProducts> {
            override fun onResponse(call: Call<ResponseProducts>, response: Response<ResponseProducts>) {
                if(response.isSuccessful){
                    val responseProducts:ResponseProducts? = response.body()
                    onResultData(responseProducts!!)
                }
            }

            override fun onFailure(call: Call<ResponseProducts>, t: Throwable) {
            }

        })
    }

    private fun onResultData(responseProducts: ResponseProducts) {
        val vertical = responseProducts.products
        ProductsAdapter.getProducts(vertical as List<ProductsItem>)
    }

}