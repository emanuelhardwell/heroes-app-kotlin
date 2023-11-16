package com.emanuel.mysecondapp.settings

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.emanuel.mysecondapp.R
import com.emanuel.mysecondapp.databinding.ActivitySettingsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingsBinding

    private var valueStart: Boolean = true

    companion object {
        const val KEY_BLUETOOTH = "key_bluetooth"
        const val KEY_NIGHTMODE = "key_nightmode"
        const val KEY_VIBRATION = "key_vibration"
        const val KEY_VOLUME = "key_volume"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_settings)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        CoroutineScope(Dispatchers.IO).launch {
            getData().filter { valueStart }.collect() { settingsModel ->
                if (settingsModel != null) {
                    runOnUiThread {
                        binding.rangeSliderVolume.setValues(settingsModel.volume.toFloat())
                        binding.switchBluetooth.isChecked = settingsModel.bluetooth
                        binding.switchNightMode.isChecked = settingsModel.nightMode
                        binding.switchVibrate.isChecked = settingsModel.vibration
                        valueStart = !valueStart
                    }
                }
            }
        }

        initUI()
    }

    private fun initUI() {
        binding.rangeSliderVolume.addOnChangeListener { _, value, _ ->
            CoroutineScope(Dispatchers.IO).launch {
                saveToVolume(value.toInt())
                //Log.i("volume", value.toString())
            }
        }

        binding.switchBluetooth.setOnCheckedChangeListener { _, isChecked ->
            CoroutineScope(Dispatchers.IO).launch {
                saveToSwitchData(KEY_BLUETOOTH, isChecked)
            }
        }

        binding.switchNightMode.setOnCheckedChangeListener { _, isChecked ->
            CoroutineScope(Dispatchers.IO).launch {
                saveToSwitchData(KEY_NIGHTMODE, isChecked)
            }
        }

        binding.switchVibrate.setOnCheckedChangeListener { _, isChecked ->
            CoroutineScope(Dispatchers.IO).launch {
                saveToSwitchData(KEY_VIBRATION, isChecked)
            }
        }
    }

    private suspend fun saveToSwitchData(keySwitch: String, value: Boolean) {
        dataStore.edit { preferences ->
            preferences[booleanPreferencesKey(keySwitch)] = value
        }
    }

    private suspend fun saveToVolume(value: Int) {
        dataStore.edit { preferences ->
            preferences[intPreferencesKey(KEY_VOLUME)] = value
        }
    }

    /*private fun getData(): Flow<Int?> {
       return dataStore.data.map {
            preferences ->
            preferences[intPreferencesKey(KEY_BLUETOOTH)]
        }
    }*/

    private fun getData(): Flow<SettingsModel> {
        return dataStore.data.map { preferences ->
            SettingsModel(
                bluetooth = preferences[booleanPreferencesKey(KEY_BLUETOOTH)] ?: false,
                nightMode = preferences[booleanPreferencesKey(KEY_NIGHTMODE)] ?: false,
                vibration = preferences[booleanPreferencesKey(KEY_VIBRATION)] ?: false,
                volume = preferences[intPreferencesKey(KEY_VOLUME)] ?: 50
            )
        }
    }

}