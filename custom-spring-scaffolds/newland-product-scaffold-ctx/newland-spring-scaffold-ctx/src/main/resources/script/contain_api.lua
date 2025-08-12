-- KEYS: list of set keys
-- ARGV[1]: the value to check
local prefix = 'api_permission:'

for _, key in ipairs(KEYS) do
    local fullKey = prefix .. key
    if redis.call('SISMEMBER', fullKey, ARGV[1]) == 1 then
        return 1  -- true
    end
end

return 0  -- false