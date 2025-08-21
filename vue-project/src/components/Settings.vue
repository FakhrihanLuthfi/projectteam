<template>
  <v-container>
    <h2 class="mb-4">Pengaturan</h2>

    <v-card class="pa-4" outlined>
      <v-form>
        <!-- Nama Kost -->
        <v-text-field
          v-model="settings.namaKost"
          label="Nama Kost"
          outlined
          dense
          class="mb-3"
        />

        <!-- Email -->
        <v-text-field
          v-model="settings.email"
          label="Email Admin"
          outlined
          dense
          class="mb-3"
        />

        <!-- Password -->
        <v-text-field
          v-model="settings.password"
          label="Password"
          type="password"
          outlined
          dense
          class="mb-3"
        />

        <!-- Dark Mode -->
        <!-- <v-switch
          v-model="settings.darkMode"
          label="Dark Mode"
          inset
          color="primary"
        /> -->

        <!-- Tombol Simpan -->
        <v-btn color="primary" class="mt-4" @click="saveSettings">
          Simpan Pengaturan
        </v-btn>
      </v-form>
    </v-card>
  </v-container>
</template>

<script setup>
import { ref } from "vue"

const settings = ref({
  namaKost: "Kost Ku",
  email: "admin@kostku.com",
  password: "",
  darkMode: false,
})

function saveSettings() {
  console.log("Pengaturan disimpan:", settings.value)
  alert("Pengaturan berhasil disimpan!")
}
</script>
