package com.example.eventmanagingapp.data

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val organizerDao: OrganizerDao // Add this DAO
) : ViewModel() {

    fun loginOrganizer(
        name: String,
        email: String,
        contact: String,
        company: String,
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
                    company = company.trim()
                )

                // Save organizer
                organizerDao.insertOrganizer(organizer)
                onSuccess(organizer)
            } catch (e: Exception) {
                onError("Login failed: ${e.message}")
            }
        }
    }
}
