package com.example.eventmanagingapp.data

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val organizerDao: OrganizerDao
) : ViewModel() {

    fun registerOrganizer(
        name: String, email: String, contact: String,
        company: String, password: String,
        onSuccess: (Organizer) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val organizer = Organizer(
                    id = email,
                    name = name.trim(),
                    email = email.trim(),
                    contact = contact.trim(),
                    company = company.trim(),
                    passwordHash = password.hashCode().toString()
                )

                organizerDao.insertOrganizer(organizer)
                onSuccess(organizer)
            } catch (e: Exception) {
                onError("Registration failed: ${e.message}")
            }
        }
    }
}