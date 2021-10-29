import Vue from "vue";
import VueRouter from "vue-router";
import LoginView from "../views/LoginView.vue";
import ForgottenView from "../views/ForgottenView.vue";
import NewUserView from "../views/NewUserView.vue";
import UserPanelView from "../views/UserPanelView.vue";
import OrganizationDetail from "../views/OrganizationDetail";

Vue.use(VueRouter);

const publicRoutes = [
  "Login",
  "Forgotten Password",
  "New user"
];

const routes = [
  {
    path: "/",
    name: "Login",
    component: LoginView,
  },
  {
    path: "/forgotten-password",
    name: "Forgotten Password",
    component: ForgottenView
  },
  {
    path: "/new-user",
    name: "New user",
    component: NewUserView
  },
  {
    path: "/userPanel",
    name: "User panel",
    component: UserPanelView,
  },
  {
    path: "/organizations/:organizationId",
    name: "Organization details",
    component: OrganizationDetail
  }
];

const router = new VueRouter({
  routes,
});

router.beforeEach((to, from, next) => {
  if ((router.app.user === undefined || router.app.user == "") && !publicRoutes.includes(to.name)) {
    next( {name: "Login" } );
  } else {
    next();
  }
});

export default router;
