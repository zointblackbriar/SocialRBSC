#!/bin/bash

################################################################################
# Solidity Version Standardization Script
################################################################################
#
# This script updates all Solidity pragma statements to use version 0.8.13
# It handles various pragma formats and ensures consistency across all contracts
#
# Usage: bash fix-solidity-versions.sh
#
################################################################################

echo "Standardizing Solidity versions to 0.8.13..."
echo ""

# Find all .sol files and update pragma statements
find . -name "*.sol" -type f | while read file; do
    echo "Processing: $file"
    
    # Backup original file
    cp "$file" "$file.bak"
    
    # Update various pragma formats to 0.8.13
    # Handle: pragma solidity ^0.8.x; -> pragma solidity 0.8.13;
    # Handle: pragma solidity >=0.8.x; -> pragma solidity 0.8.13;
    # Handle: pragma solidity >=0.7.0 <0.9.0; -> pragma solidity 0.8.13;
    
    sed -i.tmp 's/pragma solidity [^;]*;/pragma solidity 0.8.13;/g' "$file"
    rm -f "$file.tmp"
    
    # Show what changed
    if ! diff -q "$file.bak" "$file" > /dev/null 2>&1; then
        echo "  ✓ Updated"
        echo "    Before: $(grep 'pragma solidity' "$file.bak")"
        echo "    After:  $(grep 'pragma solidity' "$file")"
    else
        echo "  - No change needed"
    fi
    
    echo ""
done

echo "Solidity version standardization complete!"
echo ""
echo "Verify changes with:"
echo "  find . -name '*.sol' -type f -exec grep 'pragma solidity' {} +"
echo ""
echo "Rollback if needed:"
echo "  find . -name '*.sol.bak' -type f -exec sh -c 'mv \"\$1\" \"\${1%.bak}\"' _ {} +"
