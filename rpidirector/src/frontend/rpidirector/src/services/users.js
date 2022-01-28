import ApiService from "./api"

const UserService = {
  login(email, password) {
    ApiService.postWithHeaders("public/users/login", {},
      {
        headers: {
          Authorization: "Basic " + btoa(email + ":" + password)
        }
      })
      .then((response) => {
        if (response.status != 200) {
          alert("Error!");
        } else {
          return response.data;
        }
      })
      .then((data) => {
        window.vue.setLoggedIn(data.user);
      }).catch((error) => alert("Error: " + error))
  },
  async logout() {
    return await ApiService.post("private/users/logout")
     
  },
  async requestCode(email) {
    return await ApiService.postWithHeaders("public/users/requestRecoverPasswordCode/" + email)
    
  },
  postCode(email, code) {
    ApiService.post("public/users/postRecoverPasswordCode/" + email + "/" + code)
      .then((response) => {
        if (response.status == 200) {
          return response.data;
        } else {
          throw new Error("Failed to post code");
        }
      })
      .then((data) => {
        window.vue.setLoggedIn(data.user);
      })
      .catch((error) => alert("Error: " + error));
  },
  register(email, password, name) {
    const payload = {
      email: email,
      password: password,
      name: name,
    };

    ApiService.post("public/users/register", payload)
      .then((response) => {
        if (response.status != 200) {
          alert("Error!");
        } else {
          return response.data;
        }
      })
      .then((data) => {
        window.vue.setLoggedIn(data.user);
      }).catch((error) => alert("Error: " + error));
  }
}

export default UserService;