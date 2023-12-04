package com.example.downloadservice

import android.app.Service
import android.content.Intent
import android.os.AsyncTask
import android.os.IBinder
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.concurrent.ExecutionException

class DownloadService : Service() {
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val asyncTask = MyAsyncTask()
        try {
            asyncTask.execute("http://www.atilsamancioglu.com/").get()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    class MyAsyncTask : AsyncTask<String, Unit, String>() {

        override fun doInBackground(vararg params: String?): String {

            var result = ""

            val url : URL
            var httpURLConnection : HttpURLConnection? = null

            try {
                url = URL(params[0])
                httpURLConnection = url.openConnection() as HttpURLConnection?

                val inputStream = httpURLConnection?.inputStream
                val inputStreamReader = InputStreamReader(inputStream)
                var data = inputStreamReader.read()

                while (data != -1) {
                    // data bitene kadar okumaya devam edecek
                    val current = data.toChar()
                    result += current

                    data = inputStreamReader.read()
                }
                return result
            } catch (e: Exception) {
                return "Failed"
            }

        }

        override fun onPostExecute(result: String?) {
            println("Result $result")
            super.onPostExecute(result)
        }
    }
}