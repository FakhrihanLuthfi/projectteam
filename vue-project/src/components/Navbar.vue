<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light shadow-sm">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">My Kost</a>
      <div class="d-flex" v-if="isLoggedIn">
        <button class="btn btn-outline-primary" @click="logout">Logout</button>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

// ambil router
const router = useRouter();

// cek session login
const isLoggedIn = ref(localStorage.getItem("isLoggedIn") === "true");

const logout = () => {
  // hapus session login
  localStorage.removeItem("isLoggedIn");

  // update state supaya button hilang
  isLoggedIn.value = false;

  // redirect ke login page
  router.push({ name: "Login" });
};
</script>
