package com.dantrap.homework_5

data class ContactInfo(
    val firstName: String,
    val secondName: String,
    val number: String,
    val iconBackground: Int
) {
    companion object {
        val contactList = listOf(
            ContactInfo("Aleksandr", "Pushkin", "+79000000001", R.color.purple_200),
            ContactInfo("Alisher", "Morgenshtern", "+79000000002", R.color.teal_200),
            ContactInfo("Miron", "Yanovich", "+79000000003", R.color.orange),
            ContactInfo("Oleg", "Tinkov", "+79000000004", R.color.nuclear_waste)
        )
    }
}
