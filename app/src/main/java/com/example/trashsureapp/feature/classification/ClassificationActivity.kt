package com.example.trashsureapp.feature.classification

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import com.example.trashsureapp.R
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.trashsureapp.ml.Model
import org.tensorflow.lite.DataType
import org.tensorflow.lite.support.image.ImageProcessor
import org.tensorflow.lite.support.image.ops.ResizeOp
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer
import java.nio.ByteBuffer
import java.nio.ByteOrder

class ClassificationActivity : AppCompatActivity() {

    private lateinit var takePhoto: Button
    private lateinit var choosePhoto: Button
    private lateinit var result: TextView
    private lateinit var image: ImageView
    private lateinit var predict: Button
    private lateinit var bitmap: Bitmap

    private val labels by lazy {
        application.assets.open("labels.txt").bufferedReader().readLines()
    }

    private val model by lazy {
        Model.newInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classification)

        takePhoto = findViewById(R.id.button4)
        choosePhoto = findViewById(R.id.button5)
        result = findViewById(R.id.textView2)
        image = findViewById(R.id.imageView7)
        predict = findViewById(R.id.button)

        val imageProcessor = ImageProcessor.Builder()
            .add(ResizeOp(128, 128, ResizeOp.ResizeMethod.BILINEAR))
            .build()

        takePhoto.setOnClickListener {
            val intent: Intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, 2)
        }

        choosePhoto.setOnClickListener {
            val intent: Intent = Intent()
            intent.action = Intent.ACTION_GET_CONTENT
            intent.type = "image/*"
            startActivityForResult(Intent.createChooser(intent, "Select an Image"), 1)
        }

        predict.setOnClickListener {
            if (!::bitmap.isInitialized) {
                Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Resize the image to match the input size expected by the model (128x128 pixels)
            val resizedBitmap = Bitmap.createScaledBitmap(bitmap, 128, 128, true)

            var tensorBuffer = TensorBuffer.createFixedSize(intArrayOf(1, 128, 128, 3), DataType.FLOAT32)
            tensorBuffer.loadBuffer(convertBitmapToByteBuffer(resizedBitmap))

            // Runs model inference and gets result.
            val outputs = model.process(tensorBuffer)
            val outputFeature0 = outputs.outputFeature0AsTensorBuffer

            // Display the predicted label
            val maxIdx = getMaxIndex(outputFeature0.floatArray)
            result.text = "Predicted Label: ${labels[maxIdx]}"
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            val uri = data?.data
            bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
            image.setImageBitmap(bitmap)
        } else if (requestCode == 2) {
            bitmap = data?.extras?.get("data") as Bitmap
            image.setImageBitmap(bitmap)
        }
    }

    private fun getMaxIndex(array: FloatArray): Int {
        var maxIdx = 0
        array.forEachIndexed { index, value ->
            if (array[maxIdx] < value) {
                maxIdx = index
            }
        }
        return maxIdx
    }

    private fun convertBitmapToByteBuffer(bitmap: Bitmap): ByteBuffer {
        val byteBuffer = ByteBuffer.allocateDirect(4 * 128 * 128 * 3) // Assuming FLOAT32
        byteBuffer.order(ByteOrder.nativeOrder())
        val intValues = IntArray(128 * 128)
        bitmap.getPixels(intValues, 0, bitmap.width, 0, 0, bitmap.width, bitmap.height)

        for (pixelValue in intValues) {
            val r = (pixelValue shr 16) and 0xFF
            val g = (pixelValue shr 8) and 0xFF
            val b = pixelValue and 0xFF

            val normalizedPixelValue = (r + g + b) / 3.0f / 255.0f
            byteBuffer.putFloat(normalizedPixelValue)
        }
        return byteBuffer
    }

    override fun onDestroy() {
        // Release model resources when the activity is destroyed
        model.close()
        super.onDestroy()
    }
}
