<template>
  <v-app-bar app color="primary" dark>
    <!-- Tombol toggle sidebar -->
    <!-- <v-app-bar-nav-icon @click="$emit('toggle-drawer')" />

    <v-spacer></v-spacer> -->

    <div class="d-flex align-center pa-4">
      <v-icon size="28" class="me-2">mdi-home-city</v-icon>
      <span class="text-h6 font-weight-bold">KostEase</span>
    </div>
    <!-- Tombol Logout Putih -->
    <v-spacer></v-spacer>
    <v-btn color="white" variant="contained" @click="handleLogout">
      <v-icon left color="">mdi-logout</v-icon>
      <span class="text-white">Logout</span>
    </v-btn>
  </v-app-bar>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

function handleLogout() {
  localStorage.removeItem('isLoggedIn')
  router.push({ name: 'Login' })
}
</script>

<style scoped>
.navbar-brand {
  font-weight: bold;
  font-size: 1.25rem;
}

.v-btn {
  transition: all 0.2s;
}

.v-btn:hover {
  background-color: rgba(255, 255, 255, 0.2);
  color: #fff;
}
</style>
