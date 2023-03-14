export function userObject(userRef) {
    return {
        id: userRef.id,
        username: userRef.username,
        firstName: userRef.firstName,
        lastName: userRef.lastName,
        createdOn: userRef.createdOn,
        updatedOn: userRef.updatedOn
    }
}