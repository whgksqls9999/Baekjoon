SELECT
    ID,
    CASE
        WHEN
            RANK() OVER (ORDER BY SIZE_OF_COLONY DESC) / DATAS.COUNT <= 0.25
        THEN
            'CRITICAL'
        WHEN
            RANK() OVER (ORDER BY SIZE_OF_COLONY DESC) / DATAS.COUNT <= 0.5
        THEN
            'HIGH'
        WHEN
            RANK() OVER (ORDER BY SIZE_OF_COLONY DESC) / DATAS.COUNT <= 0.75
        THEN
            'MEDIUM'
        ELSE
            'LOW'
    END AS COLONY_NAME
FROM
    ECOLI_DATA,
    (SELECT
        COUNT(*) AS COUNT
    FROM
        ECOLI_DATA) AS DATAS
ORDER BY
    ID