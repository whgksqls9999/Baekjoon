SELECT
    P.*
FROM
    (SELECT
        *
    FROM
        PLACES
    GROUP BY
        HOST_ID
    HAVING
        COUNT(*) >= 2) AS HEAVY,
    PLACES P
WHERE
    P.HOST_ID = HEAVY.HOST_ID
ORDER BY
    P.ID