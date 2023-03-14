export function todoObject(todoRef) {
    return {
        id: todoRef.id,
        name: todoRef.name,
        description: todoRef.description,
        createdOn: todoRef.createdOn,
        updatedOn: todoRef.updatedOn
    }
}
