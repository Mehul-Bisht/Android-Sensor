package com.example.hardwaresensor

import android.graphics.Color
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.getSystemService
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity(), SensorEventListener {

    val colors = arrayOf(Color.CYAN,Color.GREEN,Color.MAGENTA,Color.YELLOW,Color.RED,Color.BLUE)
    lateinit var sensorManager: SensorManager
    lateinit var proximitySensor: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService<SensorManager>()!!
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)!!
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        if(event!!.values[0] > 0) {
            frame_top.setBackgroundColor(colors[Random.nextInt(6)])
            frame_bottom.setBackgroundColor(colors[Random.nextInt(6)])
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(
                this,proximitySensor,1000*1000
        )
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}