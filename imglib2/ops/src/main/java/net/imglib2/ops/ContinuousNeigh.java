/*

Copyright (c) 2011, Barry DeZonia.
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are met:
  * Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
  * Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
  * Neither the name of the Fiji project developers nor the
    names of its contributors may be used to endorse or promote products
    derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
POSSIBILITY OF SUCH DAMAGE.
*/

package net.imglib2.ops;


/**
 * 
 * @author Barry DeZonia
 *
 */
public class ContinuousNeigh extends Neighborhood<double[]> {

	public ContinuousNeigh(double[] keyPt, double[] negOffs, double[] posOffs) {
		super(keyPt, negOffs, posOffs);
		// TODO - do this in base class
		for (int i = 0; i < keyPt.length; i++) {
			if ((negOffs[i] < 0) || (posOffs[i] < 0))
				throw new IllegalArgumentException("ContinuousNeigh() : offsets must be nonnegative in magnitude");
		}
	}

	@Override
	public ContinuousNeigh copy() {
		return new ContinuousNeigh(
			getKeyPoint().clone(),
			getNegativeOffsets().clone(),
			getPositiveOffsets().clone());
	}

	@Override
	public void restrict(int dimNumber, double[] twoValues) {
		if ((twoValues[0] < 0) || (twoValues[1] < 0))
			throw new IllegalArgumentException("ContinuousNeigh : offsets out of range: each one must be >= 0");
		getNegativeOffsets()[dimNumber] = twoValues[0];
		getPositiveOffsets()[dimNumber] = twoValues[1];
	}

	public double size() {
		double size = 1;
		for (int i = 0; i < getNumDims(); i++) {
			size *= 1 + getNegativeOffsets()[i] + getPositiveOffsets()[i];
		}
		return size;
	}
}

