package com.example.hardwaresensor

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.core.content.getSystemService
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity(), SensorEventListener {

    lateinit var sensorManager: SensorManager
    lateinit var accelerometerSensor: Sensor

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        val bgColor = Color.rgb(
            accelToColor(event!!.values[0]),
            accelToColor(event.values[1]),
            accelToColor(event.values[2])
        )
        findViewById<FrameLayout>(R.id.bg).setBackgroundColor(bgColor)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService<SensorManager>()!!
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!!
    }

    private fun accelToColor(float: Float) : Int = (((12+float)/24)*255).roundToInt()

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
            this,accelerometerSensor,1000*1000
        )
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}