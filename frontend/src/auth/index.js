import router from "@/router";

export function login(user) {
    if (!user) {
        return
    }

    localStorage.setItem('currentUser', JSON.stringify(user))

    router.push('/todo').then(() => {
    })
}

export function logout() {
    localStorage.removeItem('currentUser')

    router.push('/').then(() => {
    })
}

export function currentUser() {
    let user = localStorage.getItem('currentUser')
    if (!user) {
        return user
    }

    return JSON.parse(user)
}