package tcc.com.br.tccfatec.service.interfaces

import retrofit2.Call
import retrofit2.http.GET
import tcc.com.br.tccfatec.model.Item

interface Service {
    @GET("5bf394b52f00005e45cfa48c")
    fun getItens(): Call<List<Item>>
}