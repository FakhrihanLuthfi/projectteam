<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm px-4">
    <div class="container-fluid">
      <!-- Brand -->
      <a class="navbar-brand d-flex align-center" href="#">
        <v-icon class="me-2">mdi-home-city</v-icon>
        My Kost
      </a>

      <!-- Toggle button untuk mobile -->
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarContent"
        aria-controls="navbarContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <!-- Menu -->
      <div class="collapse navbar-collapse justify-content-end" id="navbarContent">
        <div class="d-flex align-center" v-if="isLoggedIn">
          <v-avatar size="32" class="me-2" tile>
            <v-icon>mdi-account-circle</v-icon>
          </v-avatar>
          <span class="text-white me-3">Admin</span>
          <v-btn
            color="white"
            variant="outlined"
            size="small"
            class="d-flex align-center"
            @click="logout"
          >
            <v-icon class="me-1">mdi-logout</v-icon>
            Logout
          </v-btn>
        </div>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";
import { VIcon, VAvatar, VBtn } from "vuetify/components";

const router = useRouter();
const isLoggedIn = ref(localStorage.getItem("isLoggedIn") === "true");
const logout = () => {
  localStorage.removeItem("isLoggedIn");
  isLoggedIn.value = false;
  router.push({ name: "Login" });
};
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
