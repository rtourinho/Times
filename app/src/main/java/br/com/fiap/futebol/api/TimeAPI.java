package br.com.fiap.futebol.api;

import java.util.List;

import br.com.fiap.futebol.model.Time;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by rtourinho on 28/11/2016.
 */

public interface TimeAPI {

    @GET("/v2/57c49ba10f00007111b50c00")
    Call<List<Time>> getTimes();
}
