package com.jefferson.week6.soal2.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jefferson.week5.soal1.model.Workout
import com.jefferson.week6.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class WorkoutViewModel(
    private val userViewModel: UserViewModel? = null
) : ViewModel() {

    private val _workouts = MutableStateFlow(
        listOf(
            Workout("Morning Run", "Cardio", R.drawable.cardio, 450, added = true),
            Workout("Push Workout", "Strength", R.drawable.bolt, 452, added = true),
            Workout("Basketball", "Sport", R.drawable.sports, 500, added = true)
        )
    )
    val workouts: StateFlow<List<Workout>> = _workouts.asStateFlow()

    private val _isFormVisible = MutableStateFlow(false)
    val isFormVisible: StateFlow<Boolean> = _isFormVisible.asStateFlow()

    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name.asStateFlow()

    private val _type = MutableStateFlow("")
    val type: StateFlow<String> = _type.asStateFlow()

    private val _calories = MutableStateFlow("")
    val calories: StateFlow<String> = _calories.asStateFlow()

    private val _selectedIcon = MutableStateFlow(R.drawable.favorite)
    val selectedIcon: StateFlow<Int> = _selectedIcon.asStateFlow()

    init {
        viewModelScope.launch {
            _workouts.collect { list ->
                userViewModel?.setWorkoutCount(list.size)
            }
        }
    }

    fun showAddForm() {
        _isFormVisible.value = true
    }

    fun hideAddForm() {
        _isFormVisible.value = false
        clearForm()
    }

    private fun clearForm() {
        _name.value = ""
        _type.value = ""
        _calories.value = ""
        _selectedIcon.value = R.drawable.fire
    }

    fun updateName(value: String) {
        _name.value = value
    }

    fun updateType(value: String) {
        _type.value = value
    }

    fun updateCalories(value: String) {
        _calories.value = value.filter { it.isDigit() }
    }

    fun selectIcon(icon: Int) {
        _selectedIcon.value = icon
    }

    fun toggleWorkout(workout: Workout) {
        _workouts.update { list ->
            list.map {
                if (it.name == workout.name) it.copy(added = !it.added) else it
            }
        }
    }

    fun addWorkout(
        name: String,
        type: String,
        iconRes: Int,
        calories: Int
    ) {
        _workouts.update { list ->
            list + Workout(name, type, iconRes, calories, added = true)
        }
    }

    fun removeWorkout(workout: Workout) {
        _workouts.update { list ->
            list.filterNot { it.name == workout.name }
        }
    }

    fun saveWorkout() {
        val nameVal = _name.value.trim()
        val typeVal = _type.value.trim()
        val calVal = _calories.value.trim()

        if (nameVal.isNotEmpty() && typeVal.isNotEmpty() && calVal.isNotEmpty()) {
            addWorkout(
                name = nameVal,
                type = typeVal,
                iconRes = _selectedIcon.value,
                calories = calVal.toInt()
            )
            hideAddForm()
        }
    }
}
