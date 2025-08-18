<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const username = ref('')
const password = ref('')
const error = ref('')

const handleLogin = () => {
  if (username.value === 'admin' && password.value === '1234') {
    localStorage.setItem('isLoggedIn', 'true')
    router.push({ name: 'Dashboard' }) // langsung ke dashboard
  } else {
    error.value = 'Username atau password salah!'
  }
}
</script>

<template>
  <div class="login-wrapper">
    <!-- Kiri: Branding + Gambar -->
    <div class="login-left">
      <div class="brand-content">
        <h1 class="brand-title">
          <i class="mdi mdi-home-outline brand-icon"></i>
          KostEase
        </h1>
        <p class="brand-subtitle">Solusi mudah untuk pencatatan kost</p>
      </div>
    </div>

    <!-- Kanan: Form Login (tanpa card) -->
    <div class="login-right">
      <div class="login-content">
        <div class="login-header">
          <i class="login-icon"></i>
          <h2 class="text-primary">Selamat Datang</h2>
        </div>
        <p class="subtitle">Masukkan username dan password</p>
        <form @submit.prevent="handleLogin" class="login-form">
          <div class="input-group">
            <i class="mdi mdi-account"></i>
            <input type="text" v-model="username" placeholder="Username" required />
          </div>
          <div class="input-group">
            <i class="mdi mdi-lock"></i>
            <input type="password" v-model="password" placeholder="Password" required />
          </div>

          <button type="submit" class="btn-login">
            <i class="mdi mdi-login"></i> Masuk
          </button>
          <p v-if="error" class="error-msg">{{ error }}</p>
        </form>
      </div>
    </div>
  </div>
</template>

<style scoped>
.login-wrapper {
  display: flex;
  height: 100vh;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
}

/* Bagian kiri */
.login-left {
  flex: 1.2;
  background-image: url('https://cove-blog-id.sgp1.cdn.digitaloceanspaces.com/cove-blog-id/2022/04/image5.jpg');
  background-size: cover;
  background-position: center;
  display: flex;
  justify-content: center;
  align-items: center;
}
.brand-content {
  text-align: center;
  color: white;
}
.brand-title {
  font-size: 2.4rem;
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
}
.brand-icon {
  font-size: 2.2rem;
}
.brand-subtitle {
  opacity: 0.9;
  margin-top: 0.5rem;
  font-size: 1.2rem;
}

/* Bagian kanan */
.login-right {
  flex: 1;
  display: flex;
  justify-content: center;
  align-items: center;
  background: #f9fafb;
  padding: 0 0.5rem; /* hanya kiri-kanan 0.5rem */
}


/* Konten login (tanpa card) */
.login-content {
  width: 90%;
  max-width: 100%; /* biar full */
  padding: 2rem 2rem; /* kasih jarak aman kiri-kanan */
}


.login-header {
  display: flex;
  align-items: center;
  justify-content: center; /* ini bikin isi rata tengah */
  gap: 8px;
  margin-bottom: 0.5rem;
  text-align: center; /* supaya teksnya ikut rata tengah */
}

.login-icon {
  font-size: 1.8rem;
  color: #2563eb;
}
.login-content h2 {
  font-size: 1.6rem;
  color: #1e3a8a;
}
.subtitle {
  color: #555;
  font-size: 0.95rem;
  margin: 0.5rem 0 1.5rem;
  text-align: center; 
}

/* Form */
.login-form {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}
.input-group {
  display: flex;
  align-items: center;
  border-bottom: 2px solid #ddd;
  padding: 0.6rem 0.3rem;
  background: transparent;
  transition: border 0.3s;
}
.input-group:focus-within {
  border-color: #2563eb;
}
.input-group i {
  margin-right: 8px;
  color: #666;
  font-size: 1.2rem;
}
.input-group input {
  flex: 1;
  border: none;
  outline: none;
  font-size: 1rem;
  background: transparent;
}

/* Tombol */
.btn-login {
  margin-top: 8px;
  padding: 12px;
  border: none;
  border-radius: 8px;
  background: #2563eb;
  color: white;
  font-size: 1rem;
  font-weight: bold;
  cursor: pointer;
  transition: background 0.3s, transform 0.2s;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 6px;
}
.btn-login:hover {
  background: #1e40af;
  transform: translateY(-2px);
}

.error-msg {
  color: red;
  font-size: 0.9rem;
  margin-top: 10px;
}
</style>
