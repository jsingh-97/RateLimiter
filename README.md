# RateLimiter
Java Rate Limiter
This is the fixed window rate limiter algorithm. It used redis for data store. 
To use this make sure you have the redis installed locally and its running on default port 6379.

/access API will return the boolean if the access is granted or not.
Currenty the limits are 3 requests per 2 minute window. But you can configure it according to your needs.
