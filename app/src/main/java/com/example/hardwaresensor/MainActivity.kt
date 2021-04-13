package com.example.hardwaresensor

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity(), SensorEventListener {

    lateinit var sensorManager: SensorManager
    lateinit var accelerometerSensor: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService<SensorManager>()!!
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!!
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        Log.d("Sensor ","""
            - - -
            ax = ${event!!.values[0]}
            ay = ${event.values[1]}
            az = ${event.values[2]}
            - - -
        """.trimIndent())
        findViewById<TextView>(R.id.output).text = "- - -\nax = ${event!!.values[0]}\nay = ${event.values[1]}\naz = ${event.values[2]}\n- - -"
    }

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