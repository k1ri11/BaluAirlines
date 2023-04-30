package com.company.baluairlines.core

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.company.baluairlines.core.di.AppComponent
import com.company.myapplication.R
import com.company.myapplication.databinding.ActivityMainBinding

/**
 * класс главной активности приложения
 * @property binding xml файл главной активности
 * @property appComponent компонент приложения с основными зависимостями
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var appComponent: AppComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appComponent = App.get(applicationContext).appComponent

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
    }

    /**
     * функция для скрытия клавиатуры когда происходит нажатие в другую область на экране
     * @param event - нажатие пользователя
     */
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val view: View? = currentFocus
            if (view is EditText) {
                view.clearFocus()
                val inputManager: InputMethodManager =
                    this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0)
            }
        }
        return super.dispatchTouchEvent(event)
    }

}