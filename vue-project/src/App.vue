<script setup>
import Sidebar from './components/Sidebar.vue'
import Navbar from './components/Navbar.vue'
import Dashboard from './views/Dashboard.vue'
</script>

<template>
  <div class="d-flex">
    <!-- Sidebar -->
    <Sidebar />

    <!-- Main content -->
    <div class="flex-grow-1">
      <Navbar />
      <main class="container-fluid mt-3">
        <Dashboard />
      </main>
    </div>
  </div>
</template>

<style>
body {
  margin: 0;
}
</style>
