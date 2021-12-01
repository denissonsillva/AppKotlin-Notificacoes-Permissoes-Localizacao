package br.com.djektech.notipermislocal

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_notificacao: Button

        btn_notificacao = findViewById(R.id.btn_notificacao)

        btn_notificacao.setOnClickListener {
            notificacaoSimples("Título", "Olá, você está sendo notificado!")
        }
    }

    fun Activity.notificacaoSimples(title:String, message:String){

        val nBuilder = NotificationCompat.Builder(this, "default")

        //Definindo um ícone pequeno
        nBuilder.setSmallIcon(R.mipmap.ic_launcher)

        //Definindo o título da notificação
        nBuilder.setContentTitle(title)

        //Definindo o conteúdo da notificação
        nBuilder.setContentText(message)


        //construindo o objeto Notification
        val notificacao = nBuilder.build()

        //Acessando o serviço de notificação do sistema
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //verificando se a API Android é maior que a 26 para criar um canal de notificação
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            //criando um objeto
            val channel = NotificationChannel("default", "Canal de notificação teste",
                NotificationManager.IMPORTANCE_DEFAULT)

            //criando um canal de notificação
            notificationManager.createNotificationChannel(channel)
        }

        //enviando a notificação em si
        notificationManager.notify(1  , notificacao)

    }


}