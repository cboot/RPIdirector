import ApiService from "./api"

const OrganizationService = {
  async create(organizationName) {
    const payload = {
      name: organizationName
    }
    return await ApiService.post("private/organizations/", payload);
  },
  async getAll() {
    return await ApiService.get("private/organizations/");
  },
  async get(id) {
    return await ApiService.get("private/organizations/"+id);
  },
  async delete(id) {
    return await ApiService.delete("private/organizations/"+id);
  },
  async leave(id) {
    return await ApiService.post("private/organizations/" +id + "/leave",)
  }
}

export default OrganizationService;