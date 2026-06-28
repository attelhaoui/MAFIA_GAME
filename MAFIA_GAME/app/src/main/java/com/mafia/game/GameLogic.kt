package com.mafia.game

class GameLogic {

    // الأدوار
    private val specialRoles = listOf("Doctor", "Detective", "Godfather", "Bodyguard", "Hunter", "Jester", "Spy", "Gambler")

    // --- 1. التحقق من الأسماء ---
    fun validateName(name: String, existingNames: List<String>): String? {
        return when {
            name.isBlank() -> "الاسم فارغ!"
            existingNames.contains(name) -> "الاسم مكرر!"
            existingNames.size >= 50 -> "تم الوصول للحد الأقصى (50 لاعب)!"
            else -> null
        }
    }

    // --- 2. توزيع الأدوار ---
    fun distributeRoles(players: List<String>, mafiaCount: Int): Map<String, String> {
        val distribution = mutableMapOf<String, String>()
        val roles = mutableListOf<String>()
        repeat(mafiaCount) { roles.add("Mafia") }
        roles.addAll(specialRoles)
        val remaining = players.size - roles.size
        repeat(remaining) { roles.add("Citizen") }
        roles.shuffle()
        players.forEachIndexed { i, name -> distribution[name] = roles.getOrElse(i) { "Citizen" } }
        return distribution
    }

    // --- 3. قواعد الدكتور (منع حماية نفس الشخص مرتين) ---
    fun canProtect(target: String, lastProtected: String?): Boolean {
        return target != lastProtected
    }

    // --- 4. قواعد العراب (لا يُكشف من المحقق) ---
    fun investigate(targetRole: String): String {
        return if (targetRole == "Godfather") "Citizen" else targetRole
    }

    // --- 5. قواعد الجاسوس (القتل، التجسس، أو التخطي) ---
    fun spyAction(action: String, target: String): String {
        return when (action) {
            "Kill" -> "تمت محاولة قتل $target"
            "Spy" -> "هوية $target هي: [يتم الكشف هنا]"
            else -> "تم تخطي الحركة."
        }
    }

    // --- 6. قواعد المراهن (حفظ الهدف والتحقق) ---
    fun isGamblerWinner(gamblerTarget: String, eliminatedPlayer: String): Boolean {
        return gamblerTarget == eliminatedPlayer
    }

    // --- 7. قواعد التصويت (إضافة خيار تخطي) ---
    fun getVotingOptions(allPlayers: List<String>): List<String> {
        val options = allPlayers.toMutableList()
        options.add("Skip Vote") // خيار تخطي التصويت
        return options
    }

    // --- 8. تفاصيل الأدوار ---
    fun getRoleDetails(role: String): String {
        return when (role) {
            "Doctor" -> "الدكتور: احمِ لاعباً، لا يمكن حماية نفس الشخص مرتين."
            "Godfather" -> "العراب: زعيم المافيا، يظهر كمواطن للمحقق."
            "Spy" -> "الجاسوس: اختر بين القتل، التجسس، أو التخطي."
            "Gambler" -> "المراهن: تراهن على لاعب، إذا أُقصي تفوز."
            else -> "مواطن/دور خاص: العب بذكاء."
        }
    }
}