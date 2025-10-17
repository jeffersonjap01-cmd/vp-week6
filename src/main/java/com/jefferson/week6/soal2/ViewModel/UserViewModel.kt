package com.jefferson.week6.soal2.ViewModel

import androidx.lifecycle.ViewModel
import com.jefferson.week5.soal1.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class UserViewModel : ViewModel() {

    private val _user = MutableStateFlow(
        User(
            name = "Jamier Taniwijaya",
            age = 21,
            height = 170,
            weight = 65,
            icons = mutableListOf(3, 0, 0)
        )
    )
    val user: StateFlow<User> = _user.asStateFlow()

    private val allUsers = listOf(
        User("Evan M.", 29, 0, 0),
        User("Tanjung D.", 21, 0, 0),
        User("Alex J.", 24, 0, 0),
        User("Maria L.", 21, 0, 0),
        User("John D.", 26, 0, 0),
        User("Sophia T.", 23, 0, 0)
    )

    private val _friends = MutableStateFlow<List<User>>(
        listOf(
            allUsers[0],
            allUsers[1]
        )
    )
    val friends: StateFlow<List<User>> = _friends.asStateFlow()

    init {
        syncIcons()
    }

    fun allUsersList(): List<User> = allUsers

    fun isFriend(candidate: User): Boolean = _friends.value.any { it.name == candidate.name }

    fun addFriend(friend: User) {
        if (isFriend(friend)) return
        _friends.update { current -> current + friend }
        syncIcons()
    }

    fun removeFriend(friend: User) {
        _friends.update { current -> current.filterNot { it.name == friend.name } }
        syncIcons()
    }

    fun updateWorkoutIcon(change: Int) {
        _user.update { user ->
            val newIcons = user.icons.toMutableList().apply {
                while (this.size < 3) this.add(0)
                this[2] = (this[2] + change).coerceAtLeast(0)
            }
            user.copy(icons = newIcons)
        }
    }

    private fun syncIcons() {
        _user.update { user ->
            val newIcons = user.icons.toMutableList().apply {
                while (this.size < 3) this.add(0)
                this[1] = _friends.value.size
                this[2] = this.getOrElse(2) { 0 }.coerceAtLeast(0)
            }
            user.copy(icons = newIcons)
        }
    }

    fun setWorkoutCount(count: Int) {
        _user.update { user ->
            val newIcons = user.icons.toMutableList().apply {
                while (this.size < 3) this.add(0)
                this[2] = count.coerceAtLeast(0)
            }
            user.copy(icons = newIcons)
        }
    }
}
