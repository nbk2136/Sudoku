package com.example.sudoku

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    //クラス内のprivateメンバにアクセスできるように、コンパニオンオブジェクトを設定
    companion object {
        const val CAMERA_REQUEST_CODE = 1
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    override fun onResume(){
     super.onResume()

        shooting.setOnClickListener{
            //カメラ機能を実装したアプリが存在するかチェック
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).resolveActivity(packageManager)?.let{
                takePicture()
            }
        }
    }
    //カメラ撮影後の処理
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            val image = data?.extras?.get("data")?.let{
                cameraImage.setImageBitmap(it as Bitmap)
            }
        }
    }
    //写真を撮影する
    private fun takePicture(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).apply{
            addCategory(Intent.CATEGORY_DEFAULT)
        }
        startActivityForResult(intent, CAMERA_REQUEST_CODE)
    }
}

