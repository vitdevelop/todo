import axios from "axios";
import config from "@/main";

export async function getUserByUsername(username) {
     return axios.get(config.serviceUrl + '/api/users/info?username=' + username)
         .then(r => {
              return r.data
         }).catch(() => null)
}