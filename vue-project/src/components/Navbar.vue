<template>
  <nav class="navbar navbar-expand-lg navbar-light bg-light shadow-sm">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">My Kost</a>
      <div class="d-flex">
        <button class="btn btn-outline-primary" @click="logout">Logout</button>
      </div>
    </div>
  </nav>
</template>

<script setup>
import { useRouter } from "vue-router";

const router = useRouter();

const logout = () => {
  // hapus session login
  localStorage.removeItem("isLoggedIn");

  // redirect ke login page
  router.push({ name: "Login" });
};
</script>
