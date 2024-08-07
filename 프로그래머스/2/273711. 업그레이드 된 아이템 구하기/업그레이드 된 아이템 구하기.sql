SELECT
    CHILD.ITEM_ID, 
    CHILD.ITEM_NAME, 
    CHILD.RARITY
FROM
    ITEM_INFO PARENT
JOIN
    ITEM_TREE TREE 
ON
    PARENT.ITEM_ID = TREE.PARENT_ITEM_ID
JOIN
    ITEM_INFO CHILD
ON
    TREE.ITEM_ID = CHILD.ITEM_ID
WHERE
    PARENT.RARITY = 'RARE'
ORDER BY
    CHILD.ITEM_ID DESC