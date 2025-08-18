import { createRouter, createWebHistory } from "vue-router";
import Login from "../components/Login.vue";
import Dashboard from "../views/Dashboard.vue";

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
