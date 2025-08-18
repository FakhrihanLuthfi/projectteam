<script setup>
import { useRoute } from "vue-router";
import Sidebar from "./components/Sidebar.vue";
import Navbar from "./components/Navbar.vue";

const route = useRoute();
</script>

<template>
  <v-app>
    <!-- kalau masih di Login -->
    <div v-if="route.name === 'Login'">
      <router-view />
    </div>

    <!-- kalau sudah login -->
    <div v-else class="d-flex">
      <!-- Sidebar -->
      <Sidebar />

      <!-- Main Content -->
      <div class="flex-grow-1">
        <Navbar />
        <main class="container-fluid mt-3">
          <router-view />
        </main>
      </div>
    </div>
  </v-app>
</template>

<style>
body {
  margin: 0;
}
</style>
