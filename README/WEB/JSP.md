[WEB-SECURITY](https://portswigger.net/web-security/all-labs)

[CORS](https://www.youtube.com/watch?v=t5FBwq-kudw&list=PLuyTk2_mYISIYD48TavDgJnQRQRZZDsLZ)

***

# JavaScript Asynchronous Basics

## Callbacks

Callbacks are functions passed as arguments to other functions, to be executed later when a task is complete or when a condition is met.

### Example:
```javascript
function fetchData(callback) {
    setTimeout(() => {
        callback('Data fetched successfully');
    }, 2000);
}

fetchData((message) => {
    console.log(message); // Output: Data fetched successfully
});
```

## Promises

Promises represent the eventual completion or failure of an asynchronous operation, and its resulting value.

### Creating a Promise:
```javascript
function fetchData() {
    return new Promise((resolve, reject) => {
        setTimeout(() => {
            resolve('Data fetched successfully');
            // or reject(new Error('Failed to fetch data'));
        }, 2000);
    });
}

fetchData()
    .then((data) => {
        console.log(data); // Output: Data fetched successfully
    })
    .catch((error) => {
        console.error(error);
    });
```

## Async/Await

Async functions enable asynchronous, promise-based behavior to be written in a cleaner style, using the `await` keyword.

### Using async/await:
```javascript
async function fetchData() {
    try {
        let response = await fetch('https://api.example.com/data');
        let data = await response.json();
        return data;
    } catch (error) {
        console.error('Error fetching data:', error);
        throw error;
    }
}

fetchData()
    .then((data) => {
        console.log('Fetched data:', data);
    })
    .catch((error) => {
        console.error('Error in fetchData:', error);
    });
```

## Effective Handling of Asynchronous Operations

1. **Error Handling**: Use `try/catch` blocks with async/await or `.catch()` with promises to handle errors gracefully.

2. **Chaining**: Chain promises or use async/await for sequential execution of asynchronous tasks.

3. **Parallel Execution**: Use `Promise.all()` to execute multiple promises concurrently.

4. **Async Functions**: Prefer `async` functions for cleaner asynchronous code over nested callbacks (callback hell).

5. **Debugging**: Utilize browser developer tools (e.g., Chrome DevTools) to debug asynchronous code, inspect network requests, and handle exceptions.

