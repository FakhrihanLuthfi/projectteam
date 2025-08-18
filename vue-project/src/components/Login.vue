<script setup>
import { ref } from "vue";
import { useRouter } from "vue-router";

const router = useRouter();
const username = ref("");
const password = ref("");
const error = ref("");

const handleLogin = () => {
  if (username.value === "admin" && password.value === "1234") {
    localStorage.setItem("isLoggedIn", "true");
    router.push({ name: "Dashboard" });
  } else {
    error.value = "Username atau password salah!";
  }
};
</script>

<template>
  <div class="login-container">
    <div class="login-box">
      <h2>Login</h2>
      <p class="subtitle">Masukkan username dan password</p>

      <form @submit.prevent="handleLogin">
        <input type="text" v-model="username" placeholder="Username" required />
        <input type="password" v-model="password" placeholder="Password" required />
        <button type="submit" class="btn-login">Masuk</button>
        <p v-if="error" style="color: red; margin-top: 10px">{{ error }}</p>
      </form>
    </div>
  </div>
</template>

<style scoped>
.login-container {
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: linear-gradient(to right, #6a11cb, #2575fc);
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
}

.login-box {
  background: #fff;
  padding: 2.5rem 2rem;
  border-radius: 16px;
  width: 360px;
  text-align: center;
  box-shadow: 0 12px 30px rgba(0, 0, 0, 0.2);
  animation: fadeIn 0.8s ease-in-out;
}

.login-box h2 {
  margin-bottom: 0.5rem;
  font-size: 1.6rem;
  color: #333;
}

.subtitle {
  color: #777;
  font-size: 0.95rem;
  margin-bottom: 1.5rem;
}

input {
  width: 100%;
  padding: 12px;
  margin: 10px 0;
  border: 1px solid #ddd;
  border-radius: 8px;
  font-size: 1rem;
  transition: all 0.3s;
}

input:focus {
  border-color: #2575fc;
  outline: none;
  box-shadow: 0 0 6px rgba(37, 117, 252, 0.3);
}

.btn-login {
  margin-top: 16px;
  width: 100%;
  padding: 12px;
  border: none;
  border-radius: 8px;
  background: linear-gradient(to right, #6a11cb, #2575fc);
  color: white;
  font-size: 1rem;
  font-weight: bold;
  cursor: pointer;
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.btn-login:hover {
  transform: translateY(-2px);
  box-shadow: 0 6px 15px rgba(37, 117, 252, 0.4);
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: scale(0.95);
  }
  to {
    opacity: 1;
    transform: scale(1);
  }
}
</style>
