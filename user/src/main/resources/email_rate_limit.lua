local rateLimitKey = KEYS[1]
local attemptCountKey = KEYS[2]

if redis.call('EXISTS', rateLimitKey) == 1 then
    local ttl = redis.call('TTL', rateLimitKey)
    return {-1, ttl > 0 and ttl or 60 }
end

local currentCount = redis.call('GET', attemptCountKey)
currentCount = currentCount and tonumber(currentCount) or 0

local newCount = redis.call('INCR', attemptCountKey)

local backoffSeconds = {60, 300, 3600}
local backoffIndex = math.min(currentCount, 2) + 1

redis.call('SETEX', rateLimitKey, backoffSeconds[backoffIndex], '1')

redis.call('EXPIRE', attemptCountKey, 86400)

return { newCount, 0 }