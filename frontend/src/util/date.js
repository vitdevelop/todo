export function getPrettyDateFromTimeStamp(timestamp) {
    let date = new Date(timestamp);

    return date.toLocaleString();
}