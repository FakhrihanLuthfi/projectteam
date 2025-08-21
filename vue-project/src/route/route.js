import { createRouter, createWebHistory } from "vue-router";
import Login from "../components/Login.vue";
import Dashboard from "../views/Dashboard.vue";
import Room from "@/components/Room.vue";
import Tenant from "@/components/Tenant.vue";
import Settings from "@/components/Settings.vue";
import Help from "@/components/Help.vue";

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login,
    meta: { requiresAuth: false },
  },
  {
    path: "/dashboard",
    name: "Dashboard",
    component: Dashboard,
    meta: { requiresAuth: true }, // butuh login
  },
   { 
    path: "/rooms", 
    name: "rooms", 
    component: Room
  },
  {
    path: "/tenants",
    name: "tenants",
    component: Tenant,   // ⬅️ route daftar penyewa
    meta: { requiresAuth: true },
  },
  {
    path: "/settings", 
    name: "settings", 
    component: Settings,
    meta: { requiresAuth: true },
  },
  {
    path: "/help", 
    name: "help", 
    component: Help 
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

// Route Guard
router.beforeEach((to, from, next) => {
  const isLoggedIn = localStorage.getItem("isLoggedIn");

  if (to.meta.requiresAuth && !isLoggedIn) {
    // kalau belum login tapi mau ke dashboard → redirect ke login
    next({ name: "Login" });
  } else if (to.name === "Login" && isLoggedIn) {
    // kalau sudah login, jangan balik ke login lagi → langsung ke dashboard
    next({ name: "Dashboard" });
  } else {
    next();
  }
});

export default router;
